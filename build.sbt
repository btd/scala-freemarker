name := "circumflex-ftl"

organization := "ru.circumflex"

version := "2.1.1"

scalaVersion := "2.9.1"

scalacOptions ++= Seq("-deprecation", "-unchecked")

resolvers ++= Seq ("scala-tools snapshots" at "http://scala-tools.org/repo-snapshots")

publishTo := Some(Resolver.file("Github Pages", Path.userHome / "projects" / "maven2" asFile) (Patterns(true, Resolver.mavenStyleBasePattern)))

publishMavenStyle := true

libraryDependencies ++= Seq(
  "org.freemarker"   		% 	"freemarker"  			% "2.3.18",
  "net.liftweb"         	%% 	"lift-util"          	% "2.4-SNAPSHOT" 	% "compile",
  "net.liftweb"         	%% 	"lift-common"          	% "2.4-SNAPSHOT" 	% "compile",
  "commons-beanutils"   	% 	"commons-beanutils"     % "1.8.0",
  "commons-io"   			% 	"commons-io"      		% "2.1",
  "org.apache.commons"   	% 	"commons-lang3"      	% "3.1" 			% "test",
  "org.scala-tools.testing" %% 	"specs" 				% "1.6.9" 			% "test")