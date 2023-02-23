package com.datn.api;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageApi {

	@GetMapping("user-image/{photo}")
	public ResponseEntity<ByteArrayResource> getUserImage(@PathVariable("photo") String photo) {
		if (!photo.equals("") || photo != null) {
			try {
				Path filename = Paths.get("src/main/resources/static/user/img/user", photo);
				byte[] buffer = Files.readAllBytes(filename);
				ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
				return ResponseEntity.ok().contentLength(buffer.length)
						.contentType(MediaType.parseMediaType("image/png")).body(byteArrayResource);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("product-image/{productId}/{photo}")
	public ResponseEntity<ByteArrayResource> getProductImage(@PathVariable("productId") String productId,
			@PathVariable("photo") String photo) {
		if (!photo.equals("") || photo != null) {
			if (photo.equals("default.png")) {
				try {
					Path filename = Paths.get("src/main/resources/static/user/img/product", photo);
					byte[] buffer = Files.readAllBytes(filename);
					ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
					return ResponseEntity.ok().contentLength(buffer.length)
							.contentType(MediaType.parseMediaType("image/png")).body(byteArrayResource);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				try {
					Path filename = Paths.get("src/main/resources/static/user/img/product", productId, photo);
					byte[] buffer = Files.readAllBytes(filename);
					ByteArrayResource byteArrayResource = new ByteArrayResource(buffer);
					return ResponseEntity.ok().contentLength(buffer.length)
							.contentType(MediaType.parseMediaType("image/png")).body(byteArrayResource);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return ResponseEntity.badRequest().build();
	}
}
