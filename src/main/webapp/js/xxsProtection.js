document.querySelectorAll('.submit').forEach(function (element) {
    xssProtection(element);
})

const xssProtection = (element) => {
    element.addEventListener("mousedown", () => {
        const text = document.querySelectorAll('input[type="text"]');
        const pass = document.querySelectorAll('input[type="password"]');
        replaceChars(text);
        replaceChars(pass);
    }, false);
}

const replaceChars = (array) => {
    array.forEach(function (element) {
        element.value = element.value
            .replace(/</g, "&lt;")
            .replace(/>/g, "&gt;")
            .replace(/'/g, "&#39;")
            .replace(/"/g, "&#34;");
    })
}