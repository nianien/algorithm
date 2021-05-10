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


for scala

$!velocityTool.camelCaseName(${question.titleSlug})

//${question.frontendQuestionId}.${question.titleSlug}
${question.content}

package leetcode.editor.cn;
object $!velocityTool.camelCaseName(${question.titleSlug}) {
  def main(args : Array[String]){
  }
  ${question.code}
}