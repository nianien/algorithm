########################
settings for java
########################

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


########################
settings for python
########################

###TemFilePath###
/Users/scorpio/Workspace/scorpio/skyfalling/algorithm/src/main/scala

###CodeFileName###
$!velocityTool.camelCaseName(${question.titleSlug})

###CodeTemplate###
//${question.frontendQuestionId}.${question.titleSlug}
${question.content}
package scala.leetcode.editor.cn;

object $!velocityTool.camelCaseName(${question.titleSlug}) extends App{
  ${question.code}
  //test from here
}



########################
settings for python
########################

###TemFilePath###
/Users/scorpio/Workspace/scorpio/skyfalling/algorithm/src/main/python

###CodeFileName###
$!velocityTool.camelCaseName(${question.titleSlug})

###CodeTemplate###
# ${question.frontendQuestionId}.${question.titleSlug}
${question.content}
${question.code}
# test from here
if __name__ == '__main__':
    Solution()