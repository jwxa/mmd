package com.github.jwxa.service.impl;

import com.github.jwxa.dao.IStudyExamDao;
import com.github.jwxa.model.study.*;
import com.github.jwxa.service.IStudyExamService;
import com.github.jwxa.util.CommonConstant;
import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *五十音图 学习测试 Service层实现
 * Created by Jwxa on 2015/6/22.
 */
@Slf4j
@Service("studyExamService")
public class StudyExamServiceImpl implements IStudyExamService {
    @Resource
    private IStudyExamDao studyExamDao;

    @Cacheable(value = CommonConstant.ALPHABET_MULTIMAP )
    public Multimap<String, AliasVO> queryAliaListsByKind(String kind) {
        List<AliasVO> aliasVOList = studyExamDao.queryAliaListsByKind(kind);
        Multimap<String, AliasVO> multimap = ArrayListMultimap.create();
        for(AliasVO aliasVO:aliasVOList){
            multimap.put(aliasVO.getKind(),aliasVO);
        }
        return multimap;
    }

    public List<QuestionVO> createQuestionByAliasList(List<AliasVO> testAliasList, String createType, int questionNum) {
        //TODO 判断假名域是否为空 如果空就不继续执行
        if(questionNum==0){
            return Lists.newArrayList();
        }
        List<QuestionVO> questionVOList = Lists.newArrayList();
        if("1".equals(createType)){
            questionVOList  = createQuestionByAliasName(testAliasList,questionNum);
        }else if("2".equals(createType)){
            questionVOList =  createQuestionByPronounce(testAliasList, questionNum);
        }else{
            List<QuestionVO> randomQuestionVOList = Lists.newArrayList();
            List<QuestionVO> aliasNameQuestionVOList =createQuestionByAliasName(testAliasList, questionNum);
            List<QuestionVO> pronounceQuestionVOList =  createQuestionByPronounce(testAliasList, questionNum);
            randomQuestionVOList.addAll(aliasNameQuestionVOList);
            randomQuestionVOList.addAll(pronounceQuestionVOList);
            //再从生成的题目中抽取questionNum数量的作为正式题目
            Set<Integer> randomNumSet = createRandomNumSet(randomQuestionVOList.size()-1,questionNum,-1);
            for(Integer i :randomNumSet){
                questionVOList.add(randomQuestionVOList.get(i));
            }
        }
        return questionVOList;
    }

    /**
     * 以发音为题创建题目
     * @param testAliasList
     * @param questionNum
     * @return
     */
    private List<QuestionVO> createQuestionByPronounce(List<AliasVO> testAliasList, int questionNum) {
        List<QuestionVO> questionVOList = Lists.newArrayList();
        //将所有假名放入一个List<String>中
        List<String> aliasNameList = Lists.newArrayList();

        for(AliasVO aliasVO :testAliasList){
            aliasNameList.add(aliasVO.getAliasName());
        }
        //随机抽取题目序号
        Set<Integer> questionSeqSet = createRandomNumSet(testAliasList.size()-1,questionNum,-1);
        //遍历随机数set 获取随机到的题目信息
        int i =0;
        for(int questionSeq:questionSeqSet){
            //获取假名信息实体类 aliasVO
            AliasVO aliasVO =testAliasList.get(questionSeq);
            //随机生成错误选项在set中的序号{32,40,18}
            Set<Integer> optionSeqSet = createRandomNumSet(aliasNameList.size()-1,3,questionSeq);
            //{2,1,3,0} 将option[3] 放入正确答案 第32个放在第3个选项（option[2]） 以此类推
            Set<Integer> optionSet = createRandomNumSet(3,4,-1);
            //用于存放选项的String数组
            String[] options = new String[4];

            Iterator it = optionSet.iterator();
            Iterator seqIt = optionSeqSet.iterator();
            int s = 0;
            int correctAnswerSeq = -1;
            while(it.hasNext()){
                int seq = (Integer) it.next();
                if(seq!=3&&seqIt.hasNext()){
                    options[s] = aliasNameList.get((Integer) seqIt.next());
                }else{
                    options[s] = aliasVO.getAliasName();
                    correctAnswerSeq = s;
                }
                s++;
            }
            String kind = CommonConstant.ALPHABET_ALIAS_KIND_MAP.get(aliasVO.getKind());
            StringBuilder question = new StringBuilder("").append("以下发音为 ").append(aliasVO.getPronounce())
                                                            .append(" 的").append(kind).append("是：");
            QuestionVO questionVO = new QuestionVO(i,question.toString(),options,correctAnswerSeq,null);
            questionVOList.add(questionVO);
            i++;
        }
        return questionVOList;
    }




    /**
     * 生成不重复随机整数Set
     * @param maxNum 随机数最大的数字
     * @param num 随机数个数
     * @param filterNum 需要过滤的数字 没有则填写-1
     * @return
     */
    private Set<Integer> createRandomNumSet(int maxNum,int num,int filterNum) {
        Set<Integer> set = Sets.newLinkedHashSet();
        if(maxNum+1<num){
            return Sets.newLinkedHashSet();
        }
        while(num>0&&set.size()<num){
            int rndNum = RandomUtils.nextInt(maxNum+1);
            set.add(rndNum);
            set.remove(filterNum);
        }
        return set;
    }

        /**
         * 递归生成不重复随机数list
         * 效率过低 弃用 重写一个使用set接收随机数
         * @param maxNum 随机数最大的数字
         * @param list 用于存放随机数的list
         * @return 用于存放随机数的list
         */
    private List<Integer> createRandomNumList(int maxNum,List<Integer> list){
        checkNotNull(list);
        if(list.size()>maxNum){
            return list;
        }
        int questionSeq = RandomUtils.nextInt(maxNum);
        if (list.contains(questionSeq)){
            createRandomNumList(maxNum,list);
        }else{
            list.add(questionSeq);
        }
        return list;
    }


    /**
     * 以假名为题创建题目
     * @param testAliasList
     * @param questionNum
     * @return
     */
    private List<QuestionVO> createQuestionByAliasName(List<AliasVO> testAliasList, int questionNum) {
        List<QuestionVO> questionVOList = Lists.newArrayList();
        //将所有假名放入一个List<String>中
        List<String> pronounceNameList = Lists.newArrayList();

        for(AliasVO aliasVO :testAliasList){
            pronounceNameList.add(aliasVO.getPronounce());
        }
        //随机抽取题目序号
        Set<Integer> questionSeqSet = createRandomNumSet(testAliasList.size()-1,questionNum,-1);
        //遍历随机数set 获取随机到的题目信息
        int i =0;
        for(int questionSeq:questionSeqSet){
            //获取假名信息实体类 aliasVO
            AliasVO aliasVO =testAliasList.get(questionSeq);
            //随机生成错误选项在set中的序号{32,40,18}
            Set<Integer> optionSeqSet = createRandomNumSet(pronounceNameList.size()-1,3,questionSeq);
            //{2,1,3,0} 将option[3] 放入正确答案 第32个放在第3个选项（option[2]） 以此类推
            Set<Integer> optionSet = createRandomNumSet(3,4,-1);
            //用于存放选项的String数组
            String[] options = new String[4];

            Iterator it = optionSet.iterator();
            Iterator seqIt = optionSeqSet.iterator();
            int s = 0;
            int correctAnswerSeq = -1;
            while(it.hasNext()){
                int seq = (Integer) it.next();
                if(seq!=3&&seqIt.hasNext()){
                    options[s] = pronounceNameList.get((Integer) seqIt.next());
                }else{
                    options[s] = aliasVO.getPronounce();
                    correctAnswerSeq = s;
                }
                s++;
            }
            String kind = CommonConstant.ALPHABET_ALIAS_KIND_MAP.get(aliasVO.getKind());
            StringBuilder question = new StringBuilder("").append(kind).append(" ").append(aliasVO.getAliasName())
                    .append(" 的读音为：");
            QuestionVO questionVO = new QuestionVO(i,question.toString(),options,correctAnswerSeq,null);
            questionVOList.add(questionVO);
            i++;
        }
        return questionVOList;
    }



    @Override
    public Map<String, Map<String, WordVO>> createRandomAlphabet(ExamReqVO examReqVO) {
        Multimap<String, AliasVO> alphabetMap = queryAliaListsByKind(null);
        //测试guava table的使用
        Collection<AliasVO> alphabetList = alphabetMap.values();
        //行|列|假名List
        Table<String,String,WordVO> alphabetTable = HashBasedTable.create();
        for(AliasVO alias : alphabetList){
            String rowNum = alias.getRanksNum().substring(0,2);
            String colNum = alias.getRanksNum().substring(2,4);
            WordVO wordVO = alphabetTable.get(rowNum, colNum);
            Map<String,WordPartVO> aliasVOMap = Maps.newHashMap();
            if(wordVO!=null){
                aliasVOMap = alphabetTable.get(rowNum,colNum).getAliasVOMap();
            }else{
                wordVO = new WordVO();
            }
            aliasVOMap.put(alias.getKind(),new WordPartVO(alias.getAliasName()));
            //如果不存在罗马音 则加入
            if(!aliasVOMap.containsKey(CommonConstant.ALPHABET_ALIAS_KIND[2])){
                aliasVOMap.put(CommonConstant.ALPHABET_ALIAS_KIND[2],new WordPartVO(alias.getPronounce()));
            }
            wordVO.setAliasVOMap(aliasVOMap);
            alphabetTable.put(rowNum,colNum,wordVO);
        }
        //如果难度选择 按行来打乱
        Table<String,String,WordVO> examAlphabetTable = reorderAlphabet(alphabetTable,examReqVO);
        Map<String,Map<String,WordVO>> examMap =  examAlphabetTable.rowMap();
        return examMap;
    }

    /**
     * 根据选择的难度重新排序
     * @param alphabetTable
     * @param examReqVO
     * @return
     */
    private Table<String, String, WordVO> reorderAlphabet(Table<String, String,WordVO> alphabetTable, ExamReqVO examReqVO) {
        Table<String,String,WordVO> examAlphabetTable = HashBasedTable.create(alphabetTable);
        //1.行重排序
        if(CommonConstant.PRONUNCIATION_EXAM_CREATE_KIND[0].equals(examReqVO.getCreateType())
                ||CommonConstant.PRONUNCIATION_EXAM_CREATE_KIND[2].equals(examReqVO.getCreateType())){
            for(String row: alphabetTable.rowKeySet()){
                //K 列 V 列对应的所有单词（平假名片假名以及罗马音）
                Map<String,WordVO> rowMap = examAlphabetTable.row(row);
                //创建一个新的rowMap替代原来的
                Map<String,WordVO> newRowMap = shuffleMap(rowMap);
                //对单词进行重新排序
                for(Map.Entry<String,WordVO> entry:newRowMap.entrySet()){
                    String col = entry.getKey();
                    WordVO wordVO = entry.getValue();
                    distinguishWord(wordVO, examReqVO);
                    //设置显示与不显示的单词
                    examAlphabetTable.put(row,col,wordVO);
                }
            }

        }
        //2.列重排序
        if(CommonConstant.PRONUNCIATION_EXAM_CREATE_KIND[1].equals(examReqVO.getCreateType())
                ||CommonConstant.PRONUNCIATION_EXAM_CREATE_KIND[2].equals(examReqVO.getCreateType())){
            for(String col: alphabetTable.columnKeySet()){
                //K 列 V 列对应的所有单词（平假名片假名以及罗马音）
                Map<String,WordVO> colMap = examAlphabetTable.column(col);
                //创建一个新的colMap替代原来的
                Map<String,WordVO> newColMap = shuffleMap(colMap);
                //对单词进行重新排序
                for(Map.Entry<String,WordVO> entry:newColMap.entrySet()){
                    String row = entry.getKey();
                    WordVO wordVO = entry.getValue();
                    examAlphabetTable.put(row,col,wordVO);
                }
            }
        }
        return examAlphabetTable;
    }

    private void distinguishWord(WordVO wordVO, ExamReqVO examReqVO) {
        List<WordPartVO> shownList = Lists.newArrayList();
        List<WordPartVO> unshownList = Lists.newArrayList();

        WordPartVO hiragana = wordVO.getAliasVOMap().get(CommonConstant.ALPHABET_ALIAS_KIND[0]);//平假名
        WordPartVO katagana = wordVO.getAliasVOMap().get(CommonConstant.ALPHABET_ALIAS_KIND[1]);//片假名
        WordPartVO prounce = wordVO.getAliasVOMap().get(CommonConstant.ALPHABET_ALIAS_KIND[2]);//罗马音
        if(CommonConstant.PRONUNCIATION_EXAM_SHOW_KIND[0].equals(examReqVO.getAliasType())){
            shownList.add(hiragana);
            unshownList.add(katagana);
            unshownList.add(prounce);
        }else if(CommonConstant.PRONUNCIATION_EXAM_SHOW_KIND[1].equals(examReqVO.getAliasType())){
            shownList.add(katagana);
            unshownList.add(hiragana);
            unshownList.add(prounce);
        }else if(CommonConstant.PRONUNCIATION_EXAM_SHOW_KIND[2].equals(examReqVO.getAliasType())){
            shownList.add(hiragana);
            shownList.add(katagana);
            unshownList.add(prounce);
        }else{
            shownList.add(prounce);
            unshownList.add(hiragana);
            unshownList.add(katagana);
        }
        wordVO.setShownList(shownList);
        wordVO.setUnshownList(unshownList);
    }

    /**
     * 将原有Map无序打乱
     * @param originMap
     * @return
     */
    private <K,V> Map<K, V> shuffleMap(Map<K, V> originMap) {
        Map shuffleMap = Maps.newHashMap();
        List<V> values = Lists.newArrayList();
        for(Map.Entry<K,V> entry:originMap.entrySet()){
            V value = entry.getValue();
            values.add(value);
        }
        Collections.shuffle(values);
        int i = 0;
        if(values.size()!=originMap.size()){
            log.info("将原有Map无序打乱时发生错误，size不匹配:{}!={}",values.size(),originMap.size());
            return null;
        }
        for(Map.Entry<K,V> entry:originMap.entrySet()){
            K key =  entry.getKey();
            shuffleMap.put(key,values.get(i));
            i++;
        }
        return shuffleMap;
    }


}
