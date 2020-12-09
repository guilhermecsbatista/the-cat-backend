CREATE TABLE public.breed_image (
	id uuid NOT NULL,
	breed_id uuid NOT NULL,
	image_id uuid NOT NULL,
	created_at date NOT NULL,
	update_at date NOT NULL,
	CONSTRAINT breed_image_pk PRIMARY KEY (id),
	CONSTRAINT breed_image_un UNIQUE (breed_id,image_id),
	CONSTRAINT breed_image_breed_fk FOREIGN KEY (breed_id) REFERENCES public.breed(id),
	CONSTRAINT breed_image_image_fk FOREIGN KEY (image_id) REFERENCES public.image(id)
);