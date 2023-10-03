package com.ufund.api.ufundapi.persistance;

import java.io.IOException;

import com.ufund.api.ufundapi.model.Cupboard;
import com.ufund.api.ufundapi.model.Need;

public class NeedFileDAO implements NeedDAO{

    @Override
    public Need[] getNeeds(Cupboard cupboard) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNeeds'");
    }

    @Override
    public Need[] searchNeedsByName(String text) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchNeedsByName'");
    }

    @Override
    public Need getNeed(String id) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNeed'");
    }

    @Override
    public Need createNeed() throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createNeed'");
    }

    @Override
    public Need updateNeed(Need need) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateNeed'");
    }

    @Override
    public Need deleteNeed(String id) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteNeed'");
    }
    
}
