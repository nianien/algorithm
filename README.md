# Go Config

+ ## unset GOPATH
  + (Preferences | Languages & Frameworks | Go | GOPATH)

+ ## enable Go Modules
  + (Preferences | Languages & Frameworks | Go | Go Modules)
  + create go.mod at `src/main/go/leetcode`, `go mod init leetcode`

+ ## GOPROXY (recommended)
  + `go env -w GOPROXY=https://goproxy.cn,direct`

# LeetCode Plugin Config

> **Important**: 插件会根据登录的站点自动在 `leetcode/editor/cn` 或 `leetcode/editor/en` 下生成代码和文档，
> 这个路径由插件登录的站点决定，不受模板配置控制。模板只能配置代码根目录和文件名格式。
>
> **建议统一使用 leetcode.cn**，国内访问稳定，题库与国际站基本一致。
> 如果同时使用两个站点，相同题目的文档和代码会分别存放在 `editor/cn/` 和 `editor/en/` 下，不会冲突。

+ ## Settings for Java

  + ### TempFilePath
    `$PROJECT_DIR$/src/main/java`

  + ### CodeFileName
    `$!velocityTool.camelCaseName(${question.titleSlug})`

  + ### CodeTemplate
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

+ ## Settings for Scala

  + ### TempFilePath
    `$PROJECT_DIR$/src/main/scala`

  + ### CodeFileName
    `$!velocityTool.camelCaseName(${question.titleSlug})`

  + ### CodeTemplate
    ```
    //${question.frontendQuestionId}.${question.titleSlug}
    ${question.content}
    package leetcode.editor.cn

    object $!velocityTool.camelCaseName(${question.titleSlug}) extends App{
      ${question.code}
      //test from here
    }
    ```

+ ## Settings for Python

  + ### TempFilePath
    `$PROJECT_DIR$/src/main/python`

  + ### CodeFileName
    `$!velocityTool.camelCaseName(${question.titleSlug})`

  + ### CodeTemplate
    ```
    # ${question.frontendQuestionId}.${question.titleSlug}
    ${question.content}
    from leetcode.editor.cn.defined import *
    ${question.code}
    # test from here
    if __name__ == '__main__':
        print(Solution())
    ```

+ ## Settings for Go

  + ### TempFilePath
    `$PROJECT_DIR$/src/main/go`

  + ### CodeFileName
    `${question.frontendQuestionId}/$!velocityTool.camelCaseName(${question.titleSlug})`

  + ### CodeTemplate
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