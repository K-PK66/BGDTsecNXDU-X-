var CryptoJS = require("crypto-js");

//The function is to be used to generate random strings as keys or text for encryption later on.
function generateRandomString(length) {
    const chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
    let result = '';
    for (let i = 0; i < length; i++) {
        const randomIndex = Math.floor(Math.random() * chars.length);
        result += chars[randomIndex];
    }
    return result;
}

var message = generateRandomString(13);
var key = generateRandomString(5);

console.log("Text before encryption: " + message);
console.log("Key generated for encryption: " + key);

//Javascript中所用的crypto库一概使用计数器模式（CTR）进行加密。其将块密码变为流密码，通过递增一个加密计数器来产生连续密钥流，从而确保长时间不重复输出。这里特别要求ECB模式。

var cipherTextAES = CryptoJS.AES.encrypt(message, key,{
    mode: CryptoJS.mode.ECB,  
    padding: CryptoJS.pad.Pkcs7
}).toString();

console.log("Text after encryption: " + cipherTextAES);

var decryptedByteAES = CryptoJS.AES.decrypt(cipherTextAES, key, { 
    mode: CryptoJS.mode.ECB,  
    padding: CryptoJS.pad.Pkcs7
});
var decryptedTextAES = decryptedByteAES.toString(CryptoJS.enc.Utf8);

console.log("Text decryption finished. Before encoding: " + decryptedByteAES);

console.log("Encoded text: " + decryptedTextAES);

//decryptedTextAES=generateRandomString(13);

if (decryptedTextAES == message){
    console.log("Cipher success!");
}
else{
    console.log("Cipher failed. (FATAL: Text before & after the cipher is NOT the same)")
}
