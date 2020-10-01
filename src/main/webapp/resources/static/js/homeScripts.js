function targetCheck() {
    return (charCode !== 8 && charCode === 0 ||
        (charCode >= 48 && charCode <= 57))
}

// https://www.smashingmagazine.com/2018/01/drag-drop-file-uploader-vanilla-js/

let dropArea = document.getElementById('dropZone')
dropArea.addEventListener('dragenter', handlerFunction, false)
dropArea.addEventListener('dragleave', handlerFunction, false)
dropArea.addEventListener('dragover', handlerFunction, false)
dropArea.addEventListener('drop', handlerFunction, false)