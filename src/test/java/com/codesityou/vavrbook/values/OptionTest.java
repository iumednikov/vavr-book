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

import com.codesityou.vavrbook.models.Student;
import com.codesityou.vavrbook.services.StudentService;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import io.vavr.control.Option;

/**
 * This class demonstrates how to use Option
 * @author Iurii Mednikov
 */
class OptionTest {
    
    @Test
    void alternativeValueTest(){
        Option<String> name = Option.none();
        String result = name.getOrElse("Alejandra");
        Assertions.assertThat(result).isEqualTo("Alejandra");

        Option<Integer> number = Option.none();
        Integer sum = number.getOrElse(() -> 10 + 10);
        Assertions.assertThat(sum).isEqualTo(20);
    }

    @Test
    void alternativeOptionTest(){
        StudentService service = new StudentService();
        Option<Student> student = service.findByName("Jessica")
                                    .orElse(Option.some(new Student("Jessica", 4.0)));
        Student result = student.get();
        Assertions.assertThat(result).isEqualTo(new Student("Jessica", 4.0));
        
        Option<Integer> number = Option.none();
        Option<Integer> sum = number.orElse(() -> Option.some(100));
        Integer value = sum.get();
        Assertions.assertThat(value).isEqualTo(100);
    }

    @Test
    void mapTest(){
        StudentService service = new StudentService();
        Option<Student> student = service.findByName("Daniela");
        Option<Double> gpa = student.map(s -> s.getGpa());
        Double result = gpa.get();
        Assertions.assertThat(result).isEqualTo(3.7);
    }
}
