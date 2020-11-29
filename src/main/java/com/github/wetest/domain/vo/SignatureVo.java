package com.github.wetest.domain.vo;


/**
 * @author JZWen
 * @date 2020/8/21
 */
public class SignatureVo {

    private String signature;
    private Long timestamp;
    private String nonce;
    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }



    public static void main(String[] args) {
//        T1 t1 = new T1();
//        T2 t2 = new T2();
//        t1.();
//        t2.start();
        T1 t1 = new T1();
        new Thread(t1).start();
        System.out.println("-----------main end ------------");
    }
}


class T1 implements Runnable{
    @Override
    public void run() {
        try {
            for (int i=0; i<5; i++) {
                System.out.println("-------T1---------");
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            System.out.println("t1 ---------- error --------");
            e.printStackTrace();
        }
    }
}
class T2 implements Runnable {

    @Override
    public void run() {
        try {
            for (int i=0; i<5; i++) {
                System.out.println("-------T2---------");
                Thread.sleep(1000L);
            }
        } catch (InterruptedException e) {
            System.out.println("T2 -----------error-------------");
            e.printStackTrace();
        }
    }
}
