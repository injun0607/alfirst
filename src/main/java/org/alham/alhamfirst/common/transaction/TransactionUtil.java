package org.alham.alhamfirst.common.transaction;

public class TransactionUtil {

    /**
     * transaction 상태를 확인해
     * 보상과 Exception을 발생시킨다.
     * 두단계 트랜잭션 처리를 위한 메소드
     * 트랜잭션 wrapper -> 지정된서비스
     * @param transactionWrapper
     */


    public void checkTransactionStatus(TransactionWrapper transactionWrapper){
        //처음 transactionWrapper 를 확인하고 없으면 지정된 에러 던지기
        if(!transactionWrapper.checkObject()){
            return;
        }

    }

}
