package org.alham.alhamfirst.common.transaction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionWrapper {

    private Object checkObject = null;

    public boolean checkObject(){
        return this.checkObject != null;
    }

}
