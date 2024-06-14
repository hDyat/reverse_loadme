Java.perform(function(){

    var targetClass = Java.use("com.mobisec.dexclassloader.DoStuff");
    var path = "/data/user/0/com.mobisec.dexclassloader/code_cache/test.dex";


    // //Show 1  ==============================================================

    var url = "";
    targetClass.gu.implementation = function(){
        url = this.gu();
        console.log("url --> " + url);
    }

    // // var targetClassGf = Java.use("com.mobisec.dexclassloader.DoStuff");
    // targetClass.gf.implementation = function(){
    //     const gfReturn = this.gf();
    //     console.log("gfReturn --> " + gfReturn);
    // }

    // // var targetMethodGc = Java.use("com.mobisec.dexclassloader.DoStuff");
    // targetClass.gc.implementation = function(){
    //     const gcReturn = this.gc();
    //     console.log("gcReturn --> " + gcReturn);
    // }

    // // var targetMethodGm = Java.use("com.mobisec.dexclassloader.DoStuff");
    // targetClass.gm.implementation = function(){
    //     const gmReturn = this.gm();
    //     console.log("gmReturn --> " + gmReturn);
    // }

    // var targetMethodDc = Java.use("com.mobisec.dexclassloader.DoStuff");
    targetClass.dc.implementation = function(url){
        const dcReturn = this.dc(url);
        console.log("dcReturn --> " + dcReturn);
    }

    // //========================================================================


    // //Show 2
    // //adb shell chmod 644 /data/user/0/com.mobisec.dexclassloader/code_cache/myfile.dex // do this to ensures the file has appropriate read/write permissions.
    // targetClass.da.implementation = function(path){
    //     console.log("da being called with path: " + path);
    //     // return this.da(path);
    // }

    // Java.choose("com.mobisec.dexclassloader.DoStuff", {
    //     onMatch : function(instance) {
    //       instance.da(Java.use("java.lang.String").$new(path));
    //       return "stop";
    //     },
    //     onComplete:function() {}
    // });


    
    // targetClass.start.overload('android.content.Context', 'java.lang.String').implementation = function (ctx, flag) {
    //     console.log("[*] start method hooked.");

    //     // Modify the flag or perform any other dynamic modification
    //     var modifiedFlag = "modified_flag_value"; // Replace with your modified value

    //     // Call the original method with the modified arguments

    //     url = this.gu();
    //     console.log("[*] url :" + url);

    //     var originalPath = this.dc(this.gu());



    //     console.log("[*] the original Path :" + originalPath);
    //     this.da(path); // This is where you intercept da, you can modify the path argument here.
    //     var result = this.lc(path);
    //     console.log("[*] lc output : " + result);
    //     return result;
    // };
    
    

});