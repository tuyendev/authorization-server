window.addEventListener('DOMContentLoaded', event => {
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {

        if (localStorage.getItem('sb|sidebar-toggle') != null) {
            if (localStorage.getItem('sb|sidebar-toggle') === 'false') {
                document.body.classList.remove("sb-sidenav-toggled")
                document.body.classList.add("sidebar-collapse")
            } else {
                document.body.classList.add("sb-sidenav-toggled")
                document.body.classList.remove("sidebar-collapse")
            }
        }
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }

});