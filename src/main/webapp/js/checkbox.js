document.addEventListener('DOMContentLoaded', function() {
    // Sélectionne toutes les checkboxes avec la classe "single-select"
    const checkboxes = document.querySelectorAll('.single-select');
    checkboxes.forEach(cb => {
    cb.addEventListener('change', function() {
    if (this.checked) {
    checkboxes.forEach(other => {
    if (other !== this) other.checked = false;
});
}
});
});
});

