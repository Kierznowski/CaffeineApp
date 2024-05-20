document.addEventListener("DOMContentLoaded", function() {
        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        const priceCounter = document.getElementById('price-counter');

        function updatePrice() {
          let total = 0;
          checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
              total += parseFloat(checkbox.getAttribute('data-price'));
            }
          });
          priceCounter.textContent = `Total Price: $${total.toFixed(2)}`;
        }

        checkboxes.forEach(checkbox => {
          checkbox.addEventListener('change', updatePrice);
        });

        function handleCheckboxGroup(groupClass) {
          const groupCheckboxes = document.querySelectorAll(`.${groupClass}`);
          groupCheckboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function() {
              if (this.checked) {
                groupCheckboxes.forEach(otherCheckbox => {
                  if (otherCheckbox !== this) {
                    otherCheckbox.checked = false;
                  }
                });
              }
              updatePrice();
            });
          });
        }

        handleCheckboxGroup('bean-checkbox');
        handleCheckboxGroup('volume-checkbox');

        updatePrice();
      });