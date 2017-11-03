package org.wenxueliu;

import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

class TestReadFile {
    @Test
    public void testReadFileWithClassLoader(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("sum").getFile());
        assertTrue(file.exists());
    }

    @Test
    public void testReadFileWithClassLoader2() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("data01/sub1").getFile());
        assertTrue(file.exists());
    }

    @Test
    public void testReadFileWithResource() {
        URL url = this.getClass().getResource("/sum");
        File file = new File(url.getFile());
        assertTrue(file.exists());
    }

    @Test
    public void testReadFileWithResource2() throws IOException{
        InputStream is = this.getClass().getResourceAsStream("/data01/sub1");
        assertNotNull(is);
    }

    @Test
    public void readFileRelativePath() {
        File file = new File("src/test/resources/sum");
        assertTrue(file.exists());
    }

    @Test
    public void testReadAsStream() throws IOException{
        InputStream is = this.getClass().getResourceAsStream("/sum");
        assertNotNull(is);
    }

    @Test
    public void testReadAsStream2() throws IOException{
        InputStream is = this.getClass().getResourceAsStream("/data01/sub1");
        assertNotNull(is);
    }
}
