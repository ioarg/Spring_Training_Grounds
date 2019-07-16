$(document).ready(function() {
    $("#radio_lang_en").click(()=>{
        window.location.replace("?lang=en");
    });

    $("#radio_lang_el").click(()=>{
        window.location.replace("?lang=el");
    });
});