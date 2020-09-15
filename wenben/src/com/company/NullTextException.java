package com.company;

import java.io.IOException;

public class NullTextException extends IOException {

        public NullTextException(String message){
            super(message);
        }
        public NullTextException(String message,Throwable cause){
            super(message,cause);
        }
        public NullTextException(Throwable cause){
            super(cause);
        }
    }

