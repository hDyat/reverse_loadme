Java.perform(function(){

    const targetClass = Java.use("com.mobisec.dexclassloader.DoStuff");

    //PART 1
    // Intercept and log the return value of the gu method
    targetClass.gu.implementation = function() {
        const url = this.gu(); // Call the original method
        console.log("[*]url --> " + url);
        return url; // Return the original result
    };

    // Intercept and log the return value of the gf method
    targetClass.gf.implementation = function() {
        const gfReturn = this.gf(); // Call the original method
        console.log("[*]gfReturn --> " + gfReturn);
        return gfReturn; // Return the original result
    };

    // Intercept and log the return value of the gc method
    targetClass.gc.implementation = function() {
        const gcReturn = this.gc(); // Call the original method
        console.log("[*]gcReturn --> " + gcReturn);
        return gcReturn; // Return the original result
    };

    // Intercept and log the return value of the gm method
    targetClass.gm.implementation = function() {
        const gmReturn = this.gm(); // Call the original method
        console.log("[*]gmReturn --> " + gmReturn);
        return gmReturn; // Return the original result
    };

    // Intercept and log the return value of the dc method
    targetClass.dc.implementation = function(url) {
        const path = this.dc(url); // Call the original method with the argument
        console.log("[*]dcReturn or path --> " + path);
        return path; // Return the original result
    };

    // PART 2
    // Hook the da method
    var byteArrayField = targetClass.da.getDeclared
    targetClass.da.implementation = function(path) {
        console.log('[*] Hooking da method...');
        this.da(path);

        const decbytes = this.decbytes.value;

        // Convert byte array to a readable format (hexadecimal, for example)
        const byteArrayString = decbytes.map(byte => ('00' + (byte & 0xFF).toString(16)).slice(-2)).join('');

        console.log("[*] da_return--> " + byteArrayString);
    };

});