package es.unican.appriesgospersonales.activities.apps;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.os.Build;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import es.unican.appriegospersonales.activities.apps.AppsPresenter;
import es.unican.appriegospersonales.activities.apps.IAppsContract;
import es.unican.appriegospersonales.common.MyApplication;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1}, application = MyApplication.class)
public class AppsPresenterITest {

    private AppsPresenter sut;

    @Mock
    private IAppsContract.View viewMock;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        sut = new AppsPresenter(viewMock);
    }

//    @Test
//    public void testInitCorrecto() {
//        // IAP.1a
//        when(viewMock.getMyApplication()).thenReturn(ApplicationProvider.getApplicationContext());
//
//        sut.init();
//        verify(viewMock).showLoadCorrect(0);
//    }
}
