package ru.circumflex

import net.liftweb._
import util._
import common._

import java.io._

import _root_.freemarker.template._

/*!# The `ftl` package

Package `ftl` contains rendering methods, `ftl` for use in Circumflex Web Framework and
`ftl2xxx` to render an FTL template into `xxx`. It also maintains Freemarker configuration,
use `ftlConfig` to access it if you need custom operations, or use `ftl.configuration`
configuration parameter to provide your own implementation of FreeMarker `Configuration`.

You should import this package to use Circumflex FreeMarker Helper in your application:

    import ru.circumflex.freemarker._
 */
package object freemarker extends Loggable {

  val ftlConfig: Configuration = new DefaultConfiguration

  def ftl2string(template: String, root: Any): String = {
    val result = new StringWriter
    ftlConfig.getTemplate(template).process(root, result)
    result.toString
  }

  /*!# Configuring Object Wrapper

  Circumflex FreeMarker Helper provides facilities to make Scala objects available inside
  FreeMarker templates. These facilities are implemented inside `ScalaObjectWrapper`.

  There are couple of things which can be configured:

  * by default, all public fields can be resolved on any object (e.g. `${myObj.myField}`);
    to disable this, set `ftl.wrapper.resolveFields` configuration parameter to `false`;
  * by default, all public methods can be resolved on any object (e.g. `${myObj.myMethod("Hello")}`);
    to disable this, set `ftl.wrapper.resolveMethods` configuration parameter to `false`;
  * you can set `ftl.wrapper.delegateToDefault` configuration parameter to `true` in order to
    delegate resolving to FreeMarker's default object wrapper (`ObjectWrapper.DEFAULT_WRAPPER`);
    this can be useful if you work with Java types in your Scala applications (e.g. Java lists or
    maps); by default the delegation does not occur (`null` is returned if resolving fails).

  */
  val resolveFields = Props.getBool("ftl.wrapper.resolveFields", true)
  val resolveMethods = Props.getBool("ftl.wrapper.resolveMethods", true)
  val delegateToDefault = Props.getBool("ftl.wrapper.delegateToDefault", false)

}
