# go config #

+ ## unset GOPATH
  + (Preferences | Languages & Frameworks | Go | GOPATH)

+ ## enable GOModules 
  + (Preferences | Languages & Frameworks | Go | GOPATH)
  + create go.mod at `src/main/go/leetcode`, go mod init leetcode
  
# leetcode config #

+ ## Settings for java ##

  + ### TemFilePath ###
    `$PROJECT_DIR$/src/main/java`

  + ###CodeFileName ###
    `$!velocityTool.camelCaseName(${question.titleSlug})`

  + ###CodeTemplate ###
    ```
    //${question.frontendQuestionId}.${question.titleSlug}
    ${question.content}
    
    package leetcode.editor.cn;
    public class $!velocityTool.camelCaseName(${question.titleSlug}){
      public static void main(String[] args) {
           Solution solution = new $!velocityTool.camelCaseName(${question.titleSlug})().new Solution();
      }
      ${question.code}
    }
    ```

+ ## Settings for scala ##

  + ###TemFilePath ###
    `$PROJECT_DIR$/src/main/scala`

  + ###CodeFileName ###
    `$!velocityTool.camelCaseName(${question.titleSlug})`

  + ###CodeTemplate ###
    ```//${question.frontendQuestionId}.${question.titleSlug}
    ${question.content}
    package leetcode.editor.cn
    
    object $!velocityTool.camelCaseName(${question.titleSlug}) extends App{
      ${question.code}
      //test from here
    }
    ```



+ ## Settings for python ##

  + ###TemFilePath ###
    `$PROJECT_DIR$/src/main/python`

  + ###CodeFileName ###
    `$!velocityTool.camelCaseName(${question.titleSlug})`

  + ###CodeTemplate ###
    ```
    # ${question.frontendQuestionId}.${question.titleSlug}
    ${question.content}
    from leetcode.editor.cn.defined import *
    ${question.code}
    # test from here
    if __name__ == '__main__':
        print(Solution())
    ```



+ ## Settings for go #

  + ###TemFilePath ###
    `$PROJECT_DIR$/src/main/go`

  + ###CodeFileName ###
    `${question.frontendQuestionId}/$!velocityTool.camelCaseName(${question.titleSlug})`

  + ###CodeTemplate ###
    ```
    //${question.frontendQuestionId}.${question.titleSlug}
    ${question.content}
    package main
    import (
      "fmt"
    )
    import . "leetcode/editor/cn/defined" 
       
    ${question.code}
    //test from here
    func main() {
        fmt.Println()
    }
    ```