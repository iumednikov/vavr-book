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

package com.codesityou.vavrbook.services;

import java.util.NoSuchElementException;

import com.codesityou.vavrbook.models.Student;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;

public class StudentService {
    
    private final List<Student> students = List.of(
            new Student("Sofia", 4.2),
            new Student("Isabel", 4.5),
            new Student("Olivia", 3.8),
            new Student("Alejandra", 4.0),
            new Student("Camila", 3.5),
            new Student("Laura", 3.6),
            new Student("Valeria", 3.9),
            new Student("Victoria", 4.2),
            new Student("Gabriela", 3.7),
            new Student("Daniela", 3.7),
            new Student("Sara", 4.1)
    );

    public StudentService() {
        
    }
    
    public Either<IllegalArgumentException, List<Student>> findStudentsWithEither (String className){
        return Either.right(students);
    }

    public Option<Student> findByName (String name){
        return students.find(student -> student.getName().equalsIgnoreCase(name));
    }

    public Try<Student> findWithTry(String name){
        Option<Student> result = students.find(student -> student.getName().equalsIgnoreCase(name));
        if (result.isEmpty()) {
            return Try.failure(new NoSuchElementException());
        }
        return Try.success(result.get());
    }
    
}
