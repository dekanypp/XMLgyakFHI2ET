<?php
$error = '';
$nev = '';
$pin = '';
$gyumolcs = '';
$eletkor = '';
$onbizalom = '';

function clean_text($string)
{
	$string = trim($string);
	$string = stripslashes($string);
	$string = htmlspecialchars($string);
	return $string;
}

if(isset($_POST["submit"]))
{
	if(empty($_POST["nev"]))
	{
		$error = '<p><label>Nem adtad meg a neved!</label></p>';
	}
	else
	{
		$nev = clean_text($_POST["nev"]);
	}
	if(empty($_POST["pin"]))
	{
		$error = '<p><label>Nem adtad meg a PIN-kódod!</label></p>';
	}
	else
	{
		$pin = clean_text($_POST["pin"]);
	}	
	$gyumolcs = clean_text($_POST["gyumolcs"]);
	if(empty($_POST["eletkor"]))
	{
		$error = '<p><label>Nem adtad meg az életkorod!</label></p>';
	}
	else
	{
		$eletkor = clean_text($_POST["eletkor"]);
	}
	$onbizalom = clean_text($_POST["onbizalom"]);
	if($error == '')
	{
		$file_open = fopen("akarmi.csv", "a");
		$row_no = count(file("akarmi.csv"));
		$form_datas = array(
			'row_no' => $row_no
			'nev' => $nev
			'pin' => $pin
			'gyumolcs' => $gyumolcs
			'eletkor' => $eletkor
			'onbizalom' => $onbizalom
		);
		fputcsv($file_open, $form_datas);
		$error = '<label>Az elküldés sikeres volt!</label>';
		$nev = '';
		$pin = '';
		$gyumolcs = '';
		$eletkor = '';
		$onbizalom = '';
	}
	print $error;
}
?>
