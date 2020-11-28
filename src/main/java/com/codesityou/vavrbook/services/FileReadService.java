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

import com.codesityou.vavrbook.errors.BadFilenameException;
import io.vavr.control.Either;
public class FileReadService {
    
    public FileReadService(){
        
    }
    
    public String readFile (String fileName) throws BadFilenameException{
        if (fileName.equalsIgnoreCase("no-such-file.txt")) throw new BadFilenameException();
        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat";
        return content;
    }
    
    public Either<RuntimeException, String> readFileSafely(String fileName){
        if (fileName.equalsIgnoreCase("no-such-file.txt")) return Either.left(new BadFilenameException());
        String content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat";
        return Either.right(content);
    }
    
}
