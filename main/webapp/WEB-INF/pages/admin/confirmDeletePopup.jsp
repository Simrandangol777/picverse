
<style>
.popup-overlay {
  display: none;
  position: fixed;
  top: 0; left: 0;
  width: 100%; height: 100%;
  background: rgba(0, 0, 0, 0.6);
  z-index: 999;
  justify-content: center;
  align-items: center;
}
.popup-box {
  background: white;
  padding: 20px;
  border-radius: 8px;
  text-align: center;
  min-width: 300px;
}
.popup-buttons {
  margin-top: 15px;
}
.popup-buttons button {
  margin: 0 10px;
  padding: 8px 16px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
#confirmDelete {
  background-color: red;
  color: white;
}
</style>




<!-- Popup Script -->
<script>
let formToSubmit = null;

function openPopup(form) {
  formToSubmit = form;
  document.getElementById("customPopup").style.display = "flex";
  return false; // prevent actual form submit
}

function closePopup() {
  document.getElementById("customPopup").style.display = "none";
}

document.addEventListener("DOMContentLoaded", function () {
  const confirmBtn = document.getElementById("confirmDelete");
  if (confirmBtn) {
    confirmBtn.addEventListener("click", function () {
      if (formToSubmit) {
        formToSubmit.submit();
      }
    });
  }
});


if (window.history.replaceState) {
    window.history.replaceState(null, null, window.location.href);
}



</script>
