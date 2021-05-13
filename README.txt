for java:

$!velocityTool.camelCaseName(${question.titleSlug})

//${question.frontendQuestionId}.${question.titleSlug}
${question.content}

package leetcode.editor.cn;
public class $!velocityTool.camelCaseName(${question.titleSlug}){
  public static void main(String[] args) {
       Solution solution = new $!velocityTool.camelCaseName(${question.titleSlug})().new Solution();
  }
  ${question.code}
}


settings for scala
###TemFilePath###
/Users/scorpio/Workspace/scorpio/skyfalling/algorithm/src/main/scala/com/scorpio

###CodeFileName###
$!velocityTool.camelCaseName(${question.titleSlug})

###CodeTemplate###
//${question.frontendQuestionId}.${question.titleSlug}
${question.content}

package leetcode.editor.cn;
object $!velocityTool.camelCaseName(${question.titleSlug}) extends App{
  // entry test from here
  ${question.code}
}