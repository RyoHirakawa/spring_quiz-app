document.querySelectorAll('.choices li').forEach(item => {
    item.addEventListener('click', event => {
        const selectedChoiceIndex = parseInt(item.getAttribute('data-index'));
        
        const explanation = item.parentElement.nextElementSibling;
        console.log(selectedChoiceIndex);			
        if (selectedChoiceIndex === 0) {
            item.classList.add('correct');					
        } else {
            item.classList.add('incorrect');
        }
        
        explanation.style.display = 'block';

        // Disable further selections
        item.parentElement.querySelectorAll('li').forEach(choice => {
            choice.removeEventListener('click', event);
        });
    });
});