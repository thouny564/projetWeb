document.addEventListener("DOMContentLoaded", function() {
    document.getElementById('order-btn').addEventListener('click', function(event) {
        event.preventDefault();
        const params = new URLSearchParams(window.location.search);
        const locale = params.get('locale') || 'fr';


        let questionConfirmation = '';

        if (locale === 'fr') {
            questionConfirmation = "Confirmer la commande ?";
        } else {
            questionConfirmation = "Confirm the order ?";
        }

        if (confirm(questionConfirmation)) {
            document.getElementById('order-btn-form').submit();
        }
    });
});
