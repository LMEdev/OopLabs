CREATE TABLE IF NOT EXISTS mathematical_functions (
                                                      function_id SERIAL PRIMARY KEY,
                                                      function_name VARCHAR(255) NOT NULL,
    x_from DOUBLE PRECISION NOT NULL,
    x_to DOUBLE PRECISION NOT NULL,
    point_count INTEGER NOT NULL
    );

CREATE TABLE IF NOT EXISTS function_points (
                                               point_id SERIAL PRIMARY KEY,
                                               function_id INTEGER NOT NULL,
                                               x_value DOUBLE PRECISION NOT NULL,
                                               y_value DOUBLE PRECISION NOT NULL,
                                               FOREIGN KEY (function_id) REFERENCES mathematical_functions (function_id) ON DELETE CASCADE
    );
