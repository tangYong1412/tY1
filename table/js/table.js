function over(obj){
	obj.style.backgroundColor="#0078D7";
}
function out(obj){
	obj.style.backgroundColor="#FFFFFF";
}
function add(){
	var getTable=document.getElementById("table");
	if((document.form.name.value=="")||(document.form.age.value==null)||(document.form.sex.value=="")){
	}
	else{
		var addRow=getTable.insertRow(getTable.rows.length);
		addRow.insertCell(0).innerHTML=document.form.name.value;
		addRow.insertCell(1).innerHTML=document.form.age.value;
		addRow.insertCell(2).innerHTML=document.form.sex.value;
		addRow.insertCell(3).innerHTML='<button onclick="cut(this)">删除</button>';
	}
}
function cut(t){
	var getTable=document.getElementById("table");
	var deletTable=t.parentNode.parentNode.rowIndex;
	getTable.deleteRow(deletTable);
}