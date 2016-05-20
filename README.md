Invariants:  
A known user MUST be pre-created for use by the tests.  
The Actor, jschmoe was utilized by me.  Oddly enough
I think that the user accounts might be getting cleared out
over time.  As I had to change the credentials slightly during
development.  

Environmental Considerations: 
All tests have been developed in Java (currently running at 1.7)
on a Linux-x64 box.  This really shouldn't make a difference 
unless you try to use them on Windows, because I DID NOT fortify
these tests to be OS agnostic.  The build tool is just the latest
flavor of Maven.  mvn clean install the root of the source 
directory should in theory be all you need.  If debugging is 
necessary, there's no real bells and whistles to the TestNG 
config, so you should be good to go.  

The Firefox driver was pretty agreeable driving Firefox 43.0; 
but, as is Selenium's lot, it's flaky at best.  The only extra
distance I went to was to provide an extracted blank testAutomation
profile folder for those who wish to use it.  See Selenium.properties 



Things of note:
http://store.demoqa.com 
THIS SITE IS HORRIBLE. I mean, seriously. I see bugs everywhere
I'm pretty sure there was left shift of a currency amount that 
took a deal price from $12 (which was in and of itself a bug) 
to $12,000 dollars.  I'm pretty sure I've also seen Tax values 
mixed up with stated prices, and a couple of other things that
just made me shudder.  (A free mouse with 12000 dollar shipping 
or some such.  

The latency I experience doesn't tests help the Firefox
Drivers propensity for view based flakyness
, so I've had to season in a couple of waits in some areas 
that I don't really like.  

In addition, there's the INCREDIBLY ANNOYING issue of the WordPress
adminbar skulking about to intercept Selenium clicks on a more or
less regular basis.  Sometimes it plays nice, other times... not 
so much.  

It's also fantastic to note that you need to be careful which login
links are used.  That was fun for a bit. 

One minor quirk I haven't really worked out.  I like to keep
my test runs idempotent, however, due to the dependency on the 
premade user, and a database that's out of my control, if you 
want to re-run the suite and have the updateAccount scenario
pass, you'll have to do some manual mucking to prop up the 
invariants. I just haven't figured out a more elegant way of 
doing it yet.   
