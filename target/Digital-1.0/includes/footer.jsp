<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        // Manejo del sidebar en pantallas peque�as
        const toggleBtn = document.getElementById('toggleSidebar');
        const sidebar = document.getElementById('sidebar');

        if (toggleBtn) {
            toggleBtn.addEventListener('click', function () {
                if (sidebar) {
                    sidebar.classList.toggle('active');
                }
            });
        }

        // Mantener estado de los men�s abiertos
        const url = window.location.href;
        const navLinks = document.querySelectorAll('#sidebar .nav-link a');

        navLinks.forEach(function (link) {
            if (url.includes(link.getAttribute('href'))) {
                link.classList.add('active');

                // Abrir el acorde�n padre si existe
                const collapseEl = link.closest('.accordion-collapse');
                if (collapseEl) {
                    const collapseInstance = new bootstrap.Collapse(collapseEl, {
                        toggle: false
                    });
                    collapseInstance.show();
                }
            }
        });
    });
</script>
</body>
</html>