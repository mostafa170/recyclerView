<?php

	$db = new mysqli("localhost", "root", "", "tuto");


	$query = $db->query("select * from posts");

	$posts = [];

	while($post = $query->fetch_assoc()){
		$posts['posts'][] = $post;
	}

	echo json_encode($posts);

?>