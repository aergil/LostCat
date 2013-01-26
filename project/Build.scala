import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "LostCat"
    val appVersion      = "0.0.1-SNAPSHOT"

	val appDependencies = Seq(
	    "org.mongolink" % "mongolink" % "0.0.9-SNAPSHOT",
	    "com.amazonaws" % "aws-java-sdk" % "1.3.27",
	    "com.google.code.gson" % "gson" % "2.2.2" ,
	    "cglib" % "cglib-nodep" % "2.2.2"
	)
	    
    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
		resolvers ++= Seq(
		    "mongolink" at "http://repository-mongolink.forge.cloudbees.com/snapshot/"
		  
		    )
    )
}
