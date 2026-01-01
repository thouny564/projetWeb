document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("#signupForm");
    const passwordInput = document.querySelector("input[name='password']");
    const confirmInput = document.querySelector("#confirmPassword");
    const errorSpan = document.querySelector("#passwordError");

    // Récupérer la locale depuis l'URL
    const params = new URLSearchParams(window.location.search);
    const locale = params.get('locale') || 'fr'; // par défaut fr
    console.log("Locale actuelle :", locale);

    function checkPasswords() {
        if (passwordInput.value !== confirmInput.value) {
            errorSpan.textContent = (locale === 'fr')
                ? "Les mots de passe ne correspondent pas !"
                : "The passwords don't match!";
            errorSpan.style.display = "block";
            return false;
        } else {
            errorSpan.textContent = "";
            errorSpan.style.display = "none";
            return true;
        }
    }

    // Validation au moment de la saisie
    passwordInput.addEventListener("input", checkPasswords);
    confirmInput.addEventListener("input", checkPasswords);

    // Validation à l'envoi du formulaire
    form.addEventListener("submit", function(e) {
        if (!checkPasswords()) {
            e.preventDefault(); // Empêche l'envoi si les mots de passe ne correspondent pas
        }
    });
});
