package com.librarySystem.Demo;

import com.librarySystem.Demo.entity.User;
import com.librarySystem.Demo.utils.QRCode;
import org.junit.jupiter.api.Test;

public class test {

    @Test
    public void qrcodetest(){

        QRCode.CreateQRCode(new User());

    }
}
