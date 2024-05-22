//for sidebar
document.addEventListener("DOMContentLoaded", () => {
    const sidebar = document.querySelector("#sidebar");
    const toggleBtn = document.querySelector("#sidebar-toggle");
    const contents = document.querySelector(".contents");
    toggleBtn.addEventListener("click", () => {        
        sidebar.classList.toggle('show');        
        toggleBtn.innerHTML = sidebar.classList.contains('show') ? '←' : '→';
        if (sidebar.classList.contains('show')) {            
            contents.classList.add("sidebar-show");
        } else {
            contents.classList.remove("sidebar-show");
        }
    });
});