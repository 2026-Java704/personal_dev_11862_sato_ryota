function submitParentForm(event) {
	  // イベント発生元の要素を取得
	  const changedElement = event.target;
	  
	  // その要素が所属している form 要素を取得
	  const targetForm = changedElement.form;
	  
	  // フォームを送信する
	  targetForm.submit();
}