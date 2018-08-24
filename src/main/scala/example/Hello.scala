package example

import com.pty4j.{PtyProcessBuilder, PtyProcess}

object Hello extends Greeting with App {
  println(greeting)
  // The command to run in a PTY...
  val cmd = Array("""/usr/java/jdk-9.0.4/bin/jshell""")
// """C:\Program Files\Java\jdk-9.0.1\bin\jshell.exe""" );
  // The initial environment to pass to the PTY child process...
  val env = Array[String]()//"TERM=xterm" );
  val envs = new java.util.HashMap[String, String](System.getenv());
  envs.put("TERM", "xterm");
  val pty = PtyProcess.exec(cmd, envs, System.getProperty("user.home"))
  val os = pty.getOutputStream();
  val is = pty.getInputStream();
  println("read input stream ")
  new Thread{r =>{
    while(true){
      val b = is.read()
      if(b > 31){
        print(b.toChar)
      }else
        print("\\"+b.toInt)
      Thread.sleep(1)
    }
  }}.start()

  // ... work with the streams ...
  // wait until the PTY child process terminates...
  val result = pty.waitFor();

  // free up resources.
  pty.destroy()
}

trait Greeting {
  lazy val greeting: String = "hello"
}
