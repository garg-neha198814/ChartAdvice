1. Add Image Crop Library, https://github.com/jdamcd/android-crop
    compile 'com.soundcloud.android:android-crop:1.0.1@aar'

2. Declare CropImageActivity in your manifest file:
    <activity android:name="com.soundcloud.android.crop.CropImageActivity" />

3. Check for WRITE_EXTERNAL_PERMISSION in Android.

4. To Open IntentChooser use the code. IntentChoose include both options for pick and capture image.
    ImageUtils.getInstance().openImageChooser(this, this);

5. Implement SampledImageCallback in Activity and override the onSampledImage(String base64, String path)
    @Override
    public void onSampledImage(String base64, String path) {
        // here you have base64 of the image and path of the image.
        // Do what you want to do.
    }

6. Add the below code in your Activity by Override the onActivityResult
    private String selectedImagePath = "";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            ImageUtils.getInstance().resampleImage(this, this, ImageUtils.getInstance().getCroppedImagePath(this));
        } else if (requestCode == Crop.REQUEST_CROP && resultCode == Crop.RESULT_ERROR) {
            // Handle the error got during cropping.
        } else if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_CANCELED) {
            // Cropping canceled by user.
        } else if (requestCode == ImageUtils.IMAGE_CHOOSER_REQUEST_CODE && resultCode == RESULT_OK) {
            final boolean isCamera;
            if (data == null || data.getData() == null) {
                isCamera = true;
            } else {
                final String action = data.getAction();
                if (action == null) {
                    isCamera = false;
                } else {
                    isCamera = action.equals(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                }
            }

            if (isCamera) {
                selectedImagePath = ImageUtils.getInstance().getImagePath(this);
            } else {
                selectedImagePath = FileUtils.getPath(this, data.getData());
            }

            if ((selectedImagePath != null) && !(selectedImagePath.equals(""))) {
                // selectedImagePath is the path of selected or captured image.
                // if you want cropping
                ImageUtils.getInstance().beginCrop(this, this, selectedImagePath, false);

                // else if you want cropping as square
                // ImageUtils.getInstance().beginCrop(this, this, selectedImagePath, true);

                // else
                // ImageUtils.getInstance().resampleImage(this, this, selectedImagePath);
            }
        }
    }