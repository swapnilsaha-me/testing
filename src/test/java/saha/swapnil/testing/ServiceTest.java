package saha.swapnil.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @Mock
    Iterator<String> iterator;

    @Test
    public void test1()  {
        when(iterator.next())
                .thenReturn("1")
                .thenReturn("2")
                .thenReturn("3");

        String res = iterator.next() + " - " + iterator.next() + " - " + iterator.next();
        System.out.println(res);
    }

    @Test
    void test2() {
        List<String> list = new ArrayList<String>();
        List<String> spiedList = spy(list);

        doReturn("42").when(spiedList).get(99);
        String value = spiedList.get(99);

        assertEquals("42", value);
    }
}