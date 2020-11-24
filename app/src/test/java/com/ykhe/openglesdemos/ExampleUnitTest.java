package com.ykhe.openglesdemos;


import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    private void getIp(URI uri){
        System.out.println("scheme="+uri.getScheme()+";host="+uri.getHost()
                +";port="+uri.getPort()+";path="+uri.getPath());
    }

    @Test
    public void main() {
        try {
            getIp(new URI("tftp://172.16.0.56/1.jpg"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}