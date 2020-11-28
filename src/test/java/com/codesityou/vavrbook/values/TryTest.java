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
import com.codesityou.vavrbook.services.FileReadService;
import com.codesityou.vavrbook.services.StudentService;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import io.vavr.control.Try;

/**
 * This class demonstrates how to use Try
 * 
 * @author Iurii Mednikov
 */
class TryTest {
    
    @Test
    void createTryTest(){
        FileReadService service = new FileReadService();
        Try<String> result = Try.of(() -> service.readFile("no-such-file.txt"));
        Assertions.assertThat(result.isFailure()).isTrue();
    }

    @Test
    void getResultTest(){
        StudentService service = new StudentService();
        Try<Student> result = service.findWithTry("Valeria");
        Student student = result.get();
        Assertions.assertThat(student).isEqualTo(new Student("Valeria", 3.9));
    }

    @Test
    void chainTest(){
        StudentService service = new StudentService();
        Try<String> result = service.findWithTry("Olivia")
                                    .map(student -> student.getName())
                                    .andThen(name -> System.out.println(name));                          
    }
}
