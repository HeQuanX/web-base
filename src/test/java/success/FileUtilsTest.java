package success;

import cn.crabapples.utils.FileSaveUtils;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * TODO
 *
 * @author Mr.He
 * 2019/11/17 2:01
 * e-mail wishforyou.xia@gmail.com
 * qq 294046317
 * pc-name 29404
 */
public class FileUtilsTest {
    /**
     * 单元测试方 FileUtils.saveFile
     * @see FileSaveUtils
     * @throws IOException
     */
//    @Test
    public void saveFileTest() throws IOException {
        File file = new File("D:/1.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        String i = new FileSaveUtils("d:/1/").saveFile(multipartFile);
        System.err.println(i);
    }
}
