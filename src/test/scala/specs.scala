package ru.circumflex
package freemarker

import org.specs.Specification
import org.apache.commons.io.FileUtils
import org.apache.commons.lang3.StringUtils
import org.specs.matcher.Matcher
import java.io.File
import collection.mutable.HashMap

class CircumflexFtlSpec extends Specification {

  def beFine(root: Any) = new Matcher[String] {
    def apply(name: => String) = {
      val text = ftl2string("/" + name + ".ftl", root).trim
      val out = FileUtils.readFileToString(
        new File(this.getClass.getResource("/" + name + ".out").toURI), "UTF-8").trim
      val diffIndex = StringUtils.indexOfDifference(text, out)
      val diff = StringUtils.difference(text, out)
      (diffIndex == -1,
          "\"" + name + "\" is fine",
          "\"" + name + "\" fails at " + diffIndex + ": " + StringUtils.abbreviate(diff, 32))
    }
  }

  "Circumflex FTL" should {
    "handle simple objects" in {
      object simpleObject {
        val name = "Joe"
        val subobj = new Object {
          val name = "Smith"
        }
        override def toString = name + " " + subobj.name
      }
      
      val obj = simpleObject
      "Simple objects" must beFine(Map("obj"->obj))
    }
    "handle lists" in {
      val list = List("one", "two", "three")
      val range = 0 to 9
      "Lists" must beFine(Map("list"->list, "range" -> range))
    }
    "handle maps" in {
      val map = new HashMap[String, Any]
      map += ("one" -> "Hello to")
      map += ("two" -> HashMap("one" -> "Joe", "two" -> "Smith"))
      //val map = Map("one" -> "Hello to", "two" -> Map("one" -> "Joe", "two" -> "Smith"))
      val context = Map("map" -> map)
      println(context)
      "Maps" must beFine(context)
    }
    "provide limited support of Scala XML" in {
      val root = <root>
        <child>
          <one id="0">1</one>
            <two id="1"/>
          <three id="2">Three</three>
        </child>
          <child/>
      </root>
      "XML" must beFine(Map("root"->root))
    }
  }

}
