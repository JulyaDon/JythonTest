import org.python.util.PythonInterpreter;
import org.python.core.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * Created by July on 13.03.2018.
 */
public class Main {
    public static void main(String[] args) throws IOException, PyException {

        int[] myArr = {1, 2, 3, 4, 5};
        int[] array = {1,2,3,4};
        List myList = new ArrayList<>(Arrays.asList(array));
//        Integer i = 5;
//        Object obj = i;
//        myList.add((PyInteger)obj);
//
//        Integer y = 10;
//        Object obj1 = y;
//        myList.add((PyInteger)obj1);
//
//        Integer d = 15;
//        Object obj2 = d;
//        myList.add((PyInteger)obj2);
//
        PyObject obj = Py.java2py(myList);

        PyList plist= (PyList) getNewValue(obj);
        PythonInterpreter pi = new PythonInterpreter();
        pi.exec("from test import listTest");
        pi.exec("result = listTest([1, 2])");
//        PyList result = (PyList) pi.get("result");
//        pi.exec("print(result)");
//        System.out.println("result: " + result);
        PyFunction pf = (PyFunction)pi.get("listTest");
        System.out.println(pf.__call__(plist));
        //System.out.println(pf.__call__(new Py(val)).asInt());
    }

    public static PyObject getNewValue(Object newValue) {
        if (!(newValue instanceof Collection)) {
            throw new IllegalArgumentException();
        }
        Collection collection = (Collection) newValue;
        PyList list = new PyList();
        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object next = iter.next();
            if (!(next instanceof PyObject))
                throw new IllegalArgumentException("value of the collection is not instance of PyObject");
            list.__add__((PyObject) next);
        }
        return list;
    }
}

