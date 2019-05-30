<?php
//Apenas error
error_reporting(1);

$GLOBALS['GRAVACAO_PATH'] = "D:/RADIOS/004/FOR";
//$GLOBALS['GRAVACAO_PATH'] = $_SERVER['DOCUMENT_ROOT'] . "/monitoragravacaovm/GRAVACAO/004/FOR";
$GLOBALS['GRAVACAO_DATE'] = date('Ymd');

function openDirectory() {
    $openDir = $GLOBALS['GRAVACAO_OPENDIR_PATH'] = $GLOBALS['GRAVACAO_PATH'] . '/' . $GLOBALS['GRAVACAO_DATE'];
    $handle = opendir($openDir);
    return $handle;
}

$handle = openDirectory();

if ($handle !== false) {
    $arrayData = array();
   
    while (($file = readdir($handle)) !== false) {
        if ($file != '.' && $file != '..') {
            $filePath = $GLOBALS['GRAVACAO_OPENDIR_PATH'] . '/' . $file;
            
            array_push($arrayData, array(
            	'fileName' => $file,
            	'fileSize' => filesize($filePath),
            	'fileDateMod' => date("d/m/Y%20H:i:s", filemtime($filePath)),
            	'filePath' => $filePath
            ));
        }
    }
    
    closedir($handle);
    
    $lastFile = $arrayData[count($arrayData)-1];
    
    echo json_encode($lastFile, JSON_UNESCAPED_SLASHES);
    
} else {
    echo "Não foi possível abrir o diretório informado: " . $GLOBALS['GRAVACAO_OPENDIR_PATH'];
}