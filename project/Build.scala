import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "LostCat"
    val appVersion      = "0.2.0-SNAPSHOT"

	val appDependencies = Seq(
	    "org.mongolink" % "mongolink" % "0.0.10-SNAPSHOT",
	    "com.amazonaws" % "aws-java-sdk" % "1.3.27",
	    "com.google.code.gson" % "gson" % "2.2.2" ,
	    "cglib" % "cglib-nodep" % "2.2.2",
	    "asm" % "asm-all" % "3.3.1"
	)
	    
    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
		resolvers ++= Seq(
		    "mongolink" at "http://repository-mongolink.forge.cloudbees.com/snapshot/",
		    "sonatype snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
			"sonatype releases" at "https://oss.sonatype.org/content/groups/scala-tools/"		  
		    )
    )
   
}
