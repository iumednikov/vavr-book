/**
 * Copyright 2020 Iurii Mednikov @ https://www.iuriimednikov.com
 * 
 * Licensed under the GPL v3 License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the license at:
 * https://www.gnu.org/licenses/gpl-3.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codesityou.vavrbook.values;

import com.codesityou.vavrbook.errors.BadFilenameException;
import com.codesityou.vavrbook.models.Student;
import com.codesityou.vavrbook.services.FileReadService;
import com.codesityou.vavrbook.services.StudentService;
import io.vavr.collection.List;
import java.math.BigDecimal;
import java.math.MathContext;

import static org.assertj.core.api.Assertions.*;
import org.testng.annotations.Test;

/**
 * This class demonstrates how to use the Either class from Vavr library
 * @author Iurii Mednikov
 */
class EitherTest {
    
    @Test
    void noEitherTest(){
        FileReadService service = new FileReadService();
        assertThatThrownBy(() -> {
            String content = service.readFile("no-such-file.txt");
            System.out.println(content);
        }).isInstanceOf(BadFilenameException.class);
    }
    
    @Test
    void leftRightTest(){
        FileReadService service = new FileReadService();
        String content = service.readFileSafely("no-such-file.txt").getOrElse("");
        assertThat(content).isEmpty();
    }
    
    @Test
    void mapTest(){
        StudentService service = new StudentService();
        
        // filter students based on GPA
        List<Student> students = service.findStudentsWithEither("HISTORY201")
                .map(res -> res.filter(student -> student.getGpa() >= 4.0))
                .getOrElse(List.empty());
        assertThat(students).isNotEmpty().hasSize(5);
        
        // find an average GPA in class
        BigDecimal average = service.findStudentsWithEither("ART101")
                .map(res -> res.map(student -> student.getGpa()))
                .map(res -> res.average())
                .get()
                .map(value -> new BigDecimal(value, new MathContext(2)))
                .getOrElse(BigDecimal.ZERO);
                
       assertThat(average).isEqualByComparingTo("3.9");
    }
    
    @Test
    void filterTest(){
        StudentService service = new StudentService();
        
        BigDecimal average = service.findStudentsWithEither("MATH201")
                .filter(students -> students.nonEmpty())
                .get()
                .map(res -> res.map(student -> student.getGpa()))
                .map(res -> res.average())
                .get()
                .map(value -> new BigDecimal(value, new MathContext(2)))
                .get();
                
       assertThat(average).isEqualByComparingTo("3.9");
        
    }
    
}
