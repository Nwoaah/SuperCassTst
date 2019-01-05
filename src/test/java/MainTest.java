import com.google.gson.JsonArray;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static org.testng.Assert.*;

public class MainTest {
    static final String inpPathBeg = "src\\test\\resources\\input";
    static final String outPathBeg = "src\\out\\output";
    static final String actPathBeg = "src\\test\\resources\\actual";
    static final String pathEnd = "Tst.json";
    static final String dirWithFiles = "src\\out";


    String makePath(int number, String pathBeg) {
        return pathBeg + number + pathEnd;
    }

    @BeforeMethod
    public void clearTheOutput() {
        Main.output = new JsonArray();
    }

    @BeforeTest
    public void mkDirForOutput() {
        File file = new File(dirWithFiles);
        file.mkdir();
    }

    @Test
    public void testDoLogic() {
        int number = 1;
        String inputPath = makePath(number, inpPathBeg);
        String outputPath = makePath(number, outPathBeg);
        String actualFilePath = makePath(number, actPathBeg);


        Main.doLogicSingle(inputPath, outputPath);
        try {
            assertTrue(FileUtils.contentEquals(new File(outputPath), new File(actualFilePath)), "The files differ!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoLogic2() {
        int number = 2;
        String inputPath = makePath(number, inpPathBeg);
        String outputPath = makePath(number, outPathBeg);
        String actualFilePath = makePath(number, actPathBeg);


        Main.doLogicSingle(inputPath, outputPath);
        try {
            assertTrue(FileUtils.contentEquals(new File(outputPath), new File(actualFilePath)), "The files differ!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoLogic3() {
        int number = 3;
        String inputPath = makePath(number, inpPathBeg);
        String outputPath = makePath(number, outPathBeg);
        String actualFilePath = makePath(number, actPathBeg);


        Main.doLogicSingle(inputPath, outputPath);
        try {
            assertTrue(FileUtils.contentEquals(new File(outputPath), new File(actualFilePath)), "The files differ!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoLogic4() {
        int number = 4;
        String inputPath = makePath(number, inpPathBeg);
        String outputPath = makePath(number, outPathBeg);
        String actualFilePath = makePath(number, actPathBeg);


        Main.doLogicSingle(inputPath, outputPath);
        try {
            assertTrue(FileUtils.contentEquals(new File(outputPath), new File(actualFilePath)), "The files differ!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDoLogic5() {
        int number = 5;
        String inputPath = makePath(number, inpPathBeg);
        String outputPath = makePath(number, outPathBeg);
        String actualFilePath = makePath(number, actPathBeg);


        Main.doLogicSingle(inputPath, outputPath);
        try {
            assertTrue(FileUtils.contentEquals(new File(outputPath), new File(actualFilePath)), "The files differ!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

 /*
    @AfterTest
    public void deleteDirAndTestOutputFiles() {
        File file = new File("src\\out");
        if (file.isDirectory()) {
            for(File fi : file.listFiles()) {
                fi.delete();
            }
        }
        file.delete();
    }*/
}